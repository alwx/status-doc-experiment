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
             params-ref  :ref} @params]
        (let [html (or (get dict/refs (string/replace params-ref #"\+" "/"))
                       "<p>No description</p>")]
          [:div.popup-container
           [:div.popup.container
            [:section.content
             [:div.title
              [:h1 (string/replace params-ref #"\+" "/")]
              [:a {:href (str "/#/guides/" params-name)}
               [:img {:src "/img/close.svg"}]]]
             [:div.doc
              {:dangerouslySetInnerHTML {:__html html}}]]]])))))