(ns status-doc.scenes.root
  (:require [re-frame.core :as re-frame]
            [status-doc.scenes.index :as index-scene]
            [status-doc.scenes.snippet :as snippet-scene]))

(defn scene []
  (let [page-id (re-frame/subscribe [:get-page-id])]
    (fn []
      [:div.container
       (case @page-id
         :snippet [snippet-scene/scene]
         :index   [index-scene/scene])])))