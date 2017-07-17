(ns status-doc.routes
  (:require [goog.events :as events]
            [re-frame.core :as re-frame]
            [clojure.string])
  (:import goog.History
           goog.history.Html5History
           goog.history.EventType))

(defn- update-history! [h]
  (doto h
    (.setUseFragment true)
    (.setPathPrefix "#")
    (.setEnabled true)))

(defn new-history []
  (-> (Html5History. js/window) update-history!))

(defn app-routes []
  (let [history (new-history)]
    (events/listen history EventType.NAVIGATE
                   (fn [e]
                     (re-frame/dispatch [:set-token (.-token e)])))))