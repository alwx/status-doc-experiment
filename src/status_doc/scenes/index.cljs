(ns status-doc.scenes.index
  (:require [status-doc.dict :as dict]))


(defn scene []
  [:section.content
   [:ul
    (for [[k {:keys [metadata]}] dict/guides]
      [:li
       [:a {:href (str "#/guides/" k)} (-> metadata :title first)]])]])