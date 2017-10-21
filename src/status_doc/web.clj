(ns status-doc.web
  (:require [clojure.java.io :refer (resource)]
            [clojure.string :as string]
            [markdown.core :as md]
            [markdown.transformers :as md-transformers]))

(defn read-snippet [name]
  (-> (str "snippets/" name ".snippet")
      (resource)
      (slurp)))

(defn transform-snippet [snippet]
  (string/replace snippet
                  #"\$(.*)\$"
                  (fn [[_ link-arg]]
                    (let [[link-text link-path] (string/split link-arg #"#")]
                      (str "<a href=\"#/%ref-link%/" (string/replace link-path #"\/" "+") "\">"
                           link-text
                           "</a>")))))

(defn include-snippets [text state]
  [(string/replace text
                   #"\!\[snippets\/(.*)]"
                   (fn [res]
                     (str "<pre><code class=\"javascript\">"
                          (-> (res 1)
                              (read-snippet)
                              (transform-snippet))
                          "</code></pre>")))
   state])

(def guides-transformers (cons include-snippets md-transformers/transformer-vector))

(defmacro defguides
  [symbol-name doc-files]
  `(defonce ~symbol-name
            ~(->> doc-files
                  (map (fn [f]
                         [f (-> (str "guides/" f ".md")
                                (resource)
                                (slurp)
                                (md/md-to-html-string-with-meta
                                 :replacement-transformers guides-transformers))])))))

(defn- load-refs
  [link-prefix refs]
  (->> refs
       (map (fn [{:keys [id children] :as r}]
              [id (merge r {:html     (-> (str "references" (str link-prefix id) ".md")
                                          (resource)
                                          (slurp)
                                          (md/md-to-html-string))
                            :children (load-refs (str link-prefix id "/") children)})]))
       (into {})))

(defmacro defrefs
  [symbol-name doc-files]
  `(defonce ~symbol-name ~(load-refs "/" doc-files)))