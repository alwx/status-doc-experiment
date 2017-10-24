(ns status-doc.events.core
  (:require [re-frame.core :as re-frame]
            [status-doc.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn [_ _]
   db/app-db))

(re-frame/reg-event-db
 :set-page
 (fn [{:keys [page history] :as db} [_ id params]]
   (let [new-page    {:id     id
                      :params params}
         going-back? (= new-page (second history))]
     (cond-> db

             going-back?
             (update :history rest)

             (not going-back?)
             (update :history conj new-page)

             true
             (assoc :page {:id     id
                           :params params})))))

(re-frame/reg-event-db
 :set-token
 (fn [db [_ token]]
   (assoc db :token token)))