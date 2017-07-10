(ns status-doc.subs.core
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-active-page
  (fn [db _]
    (get db :active-page)))