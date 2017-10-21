(ns status-doc.scenes.guide
  (:require [clojure.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [status-doc.dict :as dict]))

(defn not-found []
  [:p "Not found. Please, return back to the index page."])

(defn scene []
  (let [params (re-frame/subscribe [:get-page-params])]
    (reagent/create-class
     {:component-did-mount
      (fn []
        (doseq [el (array-seq (.getElementsByClassName js/document "code-block"))]
          (.highlightBlock js/hljs el)))

      :reagent-render
      (fn []
        (let [{params-name :name} @params
              {:keys [metadata html]} (get (into {} dict/guides) params-name)
              html (if html
                     (string/replace html #"%ref-link%" (str "guides/" params-name))
                     (reagent/render-component-to-string [not-found]))]
          [:section.content
           [:div.title
            [:a {:href "/#"}
             [:img {:src "/img/back.svg"}]]
            [:h1 (or (-> metadata :title first)
                     "404")]]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]))})))