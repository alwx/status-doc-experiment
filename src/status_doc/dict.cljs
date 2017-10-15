(ns status-doc.dict
  (:require-macros [status-doc.web :refer [defguides defreferences]]))

(defguides guides ["first"])

(defreferences refs ["command/name"])