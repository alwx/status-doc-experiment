(ns status-doc.web
  (:require [clojure.java.io :refer (resource)]
            [clojure.string :as str]
            [markdown.core :as md]
            [markdown.transformers :as md-transformers]))

(defn read-snippet [name]
  (-> (str "snippets/" name ".snippet")
      (resource)
      (slurp)))

(defn transform-snippet [snippet]
  (str/replace snippet
               #"\$(.*)\$"
               (fn [[_ link-arg]]
                 (let [[link-text link-path] (str/split link-arg #"#")]
                   (str "<a href=\"#/" link-path "\">" link-text "</a>")))))

(defn include-snippets [text state]
  [(str/replace text
                #"\!\[snippets\/(.*)]"
                (fn [res]
                  (str "<pre><code class=\"javascript\">"
                       (transform-snippet (read-snippet (res 1)))
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
                         (md/md-to-html-string-with-meta :replacement-transformers guides-transformers))]))
           (into {}))))

(defmacro defreferences
  [symbol-name doc-files]
  `(defonce ~symbol-name
            ~(->> doc-files
                  (map (fn [f]
                         [f (-> (str "references/" f ".md")
                                (resource)
                                (slurp)
                                (md/md-to-html-string))]))
                  (into {}))))