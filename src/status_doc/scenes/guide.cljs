(ns status-doc.scenes.guide
  (:require [clojure.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [status-doc.dict :as dict]
            [status-doc.utils :as utils]))

(defn not-found []
  [:p "Not found. Please, return back to the index page."])

(defn scene []
  (let [params  (re-frame/subscribe [:get-page-params])
        history (re-frame/subscribe [:get-history])]
    (reagent/create-class
     {:component-did-mount
      (fn []
        (doseq [pre (array-seq (.getElementsByTagName js/document "pre"))]
          (doseq [e (array-seq (.getElementsByTagName pre "code"))]
            (.highlightBlock js/hljs e))))

      :reagent-render
      (fn []
        (let [{params-name :name} @params
              {:keys [metadata html]} (get (into {} dict/guides) params-name)
              html (if html
                     html
                     (reagent/render-component-to-string [not-found]))]
          [:section.content
           [:div.title
            [:a {:href (utils/link-back @history)}
             [:img {:src "/img/back.svg"}]]
            [:h1 (or (-> metadata :title first)
                     "404")]]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]))})))