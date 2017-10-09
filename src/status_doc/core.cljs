(ns status-doc.core
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [status-doc.scenes.root :as root-scene]
            [status-doc.events.core]
            [status-doc.subs.core]
            [status-doc.routes :as routes]))

(defn app-root []
  (r/render [root-scene/scene] (.getElementById js/document "app")))

(defn init []
  (routes/app-routes)
  (dispatch-sync [:initialize-db])
  (app-root))

(init)