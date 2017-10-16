(ns status-doc.scenes.guide
  (:require [clojure.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [status-doc.dict :as dict]))

(defn scene []
  (let [params (re-frame/subscribe [:get-page-params])]
    (reagent/create-class
     {:component-did-mount
      (fn []
        (.initHighlighting js/hljs))

      :reagent-render
      (fn []
        (let [{params-name :name} @params
              {:keys [metadata html]} (get dict/guides params-name)
              html (string/replace html #"%ref-link%" (str "guides/" params-name))]
          [:div
           [:section.content
            [:div.title
             [:a {:href "/#"}
              [:img {:src "/img/back.svg"}]]
             [:h1 (-> metadata :title first)]]
            [:div.doc
             {:dangerouslySetInnerHTML {:__html html}}]]]))})))

(defn ref-popup []
  (let [params (re-frame/subscribe [:get-page-params])]
    (fn []
      (let [{params-name :name
             params-ref  :ref} @params
            html (get dict/refs (string/replace params-ref #"\+" "/"))]
        [:div.popup-container
         [:div.popup.container
          [:section.content
           [:h1 params-ref]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]]]))))