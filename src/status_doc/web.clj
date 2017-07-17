(ns status-doc.web
  (:require [clojure.java.io :refer (resource)]
            [markdown.core :as md]))

(defmacro defsnippet
  "Reads snippet from file in `resources/snippets` dir"
  [symbol-name js-file]
  (let [content (slurp (resource (str "snippets/" js-file ".js")))]
    `(def ~symbol-name
       ~content)))

(defmacro defdocs
  [symbol-name doc-files]
  `(def ~symbol-name
     ~(->> doc-files
           (map #(vector % (-> (str "docs/" % ".md")
                               (resource)
                               (slurp)
                               (md/md-to-html-string))))
           (into {}))))