(ns status-doc.scenes.index
  (:require [clojure.string :as string]
            [re-frame.core :as re-frame]
            [status-doc.dict :as dict]))

(declare reference-links)

(defn reference-item
  [prefix level {:keys [id children]}]
  ^{:key (str "ref-" level "+" prefix "+" id)}
  [:div
   [:li
    {:class (str "level-" level)}
    [:a {:href (str "#/ref/" (string/replace (str prefix id) #"/" "+"))} id]]
   (reference-links (str prefix id "/") (inc level) children)])

(defn reference-links
  ([refs]
   (reference-links "" 0 refs))
  ([prefix level refs]
   (for [[k {:keys [id type children] :as item}] refs]
     (if (= type "separator")
       ^{:key (str "separator-" id)}
       [:div.separator]

       ^{:key (str "ref-" level "+" prefix "+" id)}
       [reference-item prefix level item]))))

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