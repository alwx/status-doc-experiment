(ns status-doc.web
  (:require [clojure.java.io :refer (resource)]))

(defmacro defjs
  "Reads JS from file in `resources` dir"
  [symbol-name js-file]
  (let [content (slurp (resource (str "snippets/" js-file)))]
    `(def ~symbol-name
       ~content)))