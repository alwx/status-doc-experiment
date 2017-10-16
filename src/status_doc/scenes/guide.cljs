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
        (let [{:keys [metadata html]} (get dict/guides (:name @params))]
          [:section.content
           [:div.title
            [:a {:href "/#"}
             [:img {:src "/img/back.svg"}]]
            [:h1 (-> metadata :title first)]]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]))})))