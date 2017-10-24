(ns status-doc.routes
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [re-frame.core :as re-frame]
            [clojure.string])
  (:import goog.History
           goog.history.Html5History
           goog.history.EventType))

(secretary/set-config! :prefix "#")

(defroute index-route "/" []
  (re-frame/dispatch [:set-page :index]))

(defroute guide-route "/guides/:name" [name]
  (re-frame/dispatch [:set-page :guide {:name name}]))

(defroute ref-route "/ref/:name" [name]
  (re-frame/dispatch [:set-page :reference {:name name}]))

(defn app-routes []
  (doto (History.)
    (events/listen
     EventType.NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))