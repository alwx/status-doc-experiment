(ns status-doc.events.core
  (:require [re-frame.core :refer [reg-event-db reg-event-fx dispatch after]]
            [status-doc.db :as db]))

(reg-event-db
  :initialize-db
  (fn [_ _]
    db/app-db))

(reg-event-db
  :set-active-page
  (fn [db [_ active-page]]
    (assoc db :active-page active-page)))