(ns status-doc.web
  (:require [clojure.java.io :refer (resource)]
            [markdown.core :as md]))

(defmacro defsnippets
  [symbol-name js-files]
  `(def ~symbol-name
     ~(->> js-files
           (map #(vector % (-> (str "snippets/" % ".snippet")
                               (resource)
                               (slurp))))
           (into {}))))

(defmacro defdocs
  [symbol-name doc-files]
  `(def ~symbol-name
     ~(->> doc-files
           (map #(vector % (-> (str "docs/" % ".md")
                               (resource)
                               (slurp)
                               (md/md-to-html-string))))
           (into {}))))