(ns status-doc.scenes.index
  (:require [clojure.string :as string]
            [status-doc.dict :as dict]))

(defn reference-links
  ([refs]
   (reference-links "" 0 refs))
  ([prefix level refs]
   (for [[_ {:keys [id children]}] refs]
     ^{:key (str "ref-" id)}
     [:div
      [:li
       {:class (str "level-" level)}
       [:a {:href (str "#/ref/" (string/replace (str prefix id) #"/" "+"))} id]]
      (reference-links (str prefix id "/") (inc level) children)])))

(defn scene []
  [:section.content
   [:div.row
    [:div.six.columns.gray-column
     [:h1 "Guides"]
     [:ul
      (for [[k {:keys [metadata]}] dict/guides]
        ^{:key (str "guide-" k)}
        [:li
         [:a {:href (str "#/guides/" k)} (-> metadata :title first)]])]]
    [:div.six.columns.gray-column
     [:h1 "Reference"]
     [:ul
      (reference-links dict/refs)]]]])