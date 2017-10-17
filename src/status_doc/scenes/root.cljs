(ns status-doc.scenes.root
  (:require [re-frame.core :as re-frame]
            [status-doc.scenes.index :as index-scene]
            [status-doc.scenes.guide :as guide-scene]
            [status-doc.scenes.reference :as reference-scene]))

(defn logo []
  [:div.container
   [:header
    [:a.logo
     [:span.logo__icon]
     [:span.logo__text "Status"
      [:span.logo__text__greyed "Developer Center"]]]]])

(defn scene []
  (let [page-id (re-frame/subscribe [:get-page-id])
        params  (re-frame/subscribe [:get-page-params])]
    (fn []
      (let [popup-opened? (and (= @page-id :guide)
                               (:ref @params))]
        [:div
         [logo]
         [:div.container
          (case @page-id
            :index [index-scene/scene]
            :guide [guide-scene/scene]
            :reference [reference-scene/scene])]
         (when popup-opened?
           [guide-scene/ref-popup])]))))