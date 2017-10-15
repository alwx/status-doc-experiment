(ns status-doc.events.core
  (:require [re-frame.core :as re-frame]
            [status-doc.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn [_ _]
   db/app-db))

(re-frame/reg-event-db
 :set-page
 (fn [db [_ id params]]
   (assoc db :page {:id     id
                    :params params})))

(re-frame/reg-event-db
 :set-token
 (fn [db [_ token]]
   (assoc db :token token)))