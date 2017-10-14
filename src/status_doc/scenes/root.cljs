(ns status-doc.scenes.root
  (:require [re-frame.core :as re-frame]
            [status-doc.scenes.index :as index-scene]
            [status-doc.scenes.guide :as guide-scene]))

(defn scene []
  (let [page-id (re-frame/subscribe [:get-page-id])]
    (fn []
      [:div.container
       (case @page-id
         :index [index-scene/scene]
         :guide [guide-scene/scene])])))