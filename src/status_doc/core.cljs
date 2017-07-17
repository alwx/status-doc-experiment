(ns status-doc.core
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [status-doc.scenes.main :as main]
            [status-doc.events.core]
            [status-doc.subs.core]
            [status-doc.routes :as routes]))

(defn app-root []
  (r/render [main/main-scene] (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (dispatch-sync [:initialize-db])
  (app-root))

(init)