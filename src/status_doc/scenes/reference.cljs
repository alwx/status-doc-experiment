(ns status-doc.scenes.reference
  (:require [clojure.string :as string]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [status-doc.dict :as dict]))

(defn- extract-from-dict
  ([path dict]
   (let [{:keys [html children] :as next} (get (into {} dict) (first path))]
     (if (= (count path) 1)
       html
       (extract-from-dict (rest path) children))))
  ([path]
   (extract-from-dict path dict/refs)))

(defn not-found []
  [:p "Not found. Please, return back to the index page."])

(defn scene []
  (let [params (re-frame/subscribe [:get-page-params])]
    (reagent/create-class
     {:component-did-mount
      (fn []
        (doseq [pre (array-seq (.getElementsByTagName js/document "pre"))]
          (doseq [e (array-seq (.getElementsByTagName pre "code"))]
            (.highlightBlock js/hljs e))))

      :reagent-render
      (fn []
        (let [name (string/replace (:name @params) #"\+" ".")
              html (or (extract-from-dict (-> (:name @params)
                                              (string/split #"\+")))
                       (reagent/render-component-to-string [not-found]))]
          [:section.content
           [:div.title
            [:a {:href "/#"}
             [:img {:src "/img/back.svg"}]]
            [:h1 name]]
           [:div.doc
            {:dangerouslySetInnerHTML {:__html html}}]]))})))

(defn ref-popup []
  (let [params (re-frame/subscribe [:get-page-params])]
    (fn []
      (let [{params-name :name
             params-ref  :ref} @params]
        (let [name (string/replace params-ref #"\+" " / ")
              html (or (extract-from-dict (string/split params-ref #"\+"))
                       (reagent/render-component-to-string [not-found]))]
          [:div.popup-container
           [:div.popup.container
            [:section.content
             [:div.title
              [:h1 name]
              [:a {:href (str "/#/guides/" params-name)}
               [:img {:src "/img/close.svg"}]]]
             [:div.doc
              {:dangerouslySetInnerHTML {:__html html}}]]]])))))