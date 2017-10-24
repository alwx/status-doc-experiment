(ns status-doc.utils
  (:require [status-doc.routes :as routes]))

(defn link-back [history]
  (if (= (count history) 1)
    "/#"
    (let [{:keys [id params]} (second history)]
      (condp = id
        :index (routes/index-route)
        :guide (routes/guide-route params)
        :reference (routes/ref-route params)
        "/#"))
    )
  )