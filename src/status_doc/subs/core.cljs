(ns status-doc.subs.core
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :get-page-id
 (fn [db _]
   (get-in db [:page :id])))

(reg-sub
 :get-page-params
 (fn [db _]
   (get-in db [:page :params])))

(reg-sub
 :get-history
 (fn [db _]
   (get db :history)))

(reg-sub
 :get-token
 (fn [db _]
   (get db :token)))

(reg-sub
 :get-snippet
 (fn [db _]
   (get db :snippet)))
