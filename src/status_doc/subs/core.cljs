(ns status-doc.subs.core
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-token
  (fn [db _]
    (get db :token)))