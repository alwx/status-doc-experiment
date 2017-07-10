(ns status-doc.core
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [status-doc.scenes.page :as page]
            [status-doc.events.core]
            [status-doc.subs.core]))

(defn app-root []
  (r/render [page/page-scene] (.getElementById js/document "app")))

(defn init []
  (dispatch-sync [:initialize-db])
  (app-root))

(init)