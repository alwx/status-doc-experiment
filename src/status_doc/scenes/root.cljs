(ns status-doc.scenes.root
  (:require [re-frame.core :as re-frame]
            [status-doc.scenes.index :as index-scene]
            [status-doc.scenes.guide :as guide-scene]
            [status-doc.scenes.reference :as reference-scene]))

(defn header []
  [:div.container
   [:header
    [:a.logo
     [:span.logo__icon]
     [:span.logo__text "Status"
      [:span.logo__text__greyed "Developer Center"]]]]])

(defn footer []
  [:div.container
   [:footer
    [:div.copyright
     [:p [:strong "Status Research & Development"]]
     [:p "GmbH Baarerstrasse 10"]
     [:p "Zug, Switzerland"]]]])

(defn scene []
  (let [page-id (re-frame/subscribe [:get-page-id])
        params  (re-frame/subscribe [:get-page-params])]
    (fn []
      (let [popup-opened? (and (= @page-id :guide)
                               (:ref @params))]
        [:div
         [header]
         [:div.container
          (case @page-id
            :index [index-scene/scene]
            :guide [guide-scene/scene]
            :reference [reference-scene/scene])]
         [footer]
         (when popup-opened?
           [reference-scene/ref-popup])]))))