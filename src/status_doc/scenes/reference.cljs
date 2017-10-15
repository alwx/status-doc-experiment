(ns status-doc.scenes.reference
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
        (let [name (-> (:name @params)
                       (string/replace #"\+" "/"))
              html (get dict/refs name)]
          [:section.content
           [:h1 name]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]))})))