(ns status-doc.scenes.page
  (:require-macros [status-doc.web :refer [defjs]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]))

(defjs example-0 "example-0.js")

(defn page-scene []
  (let [active-page (subscribe [:get-active-page])]
    (fn []
      [:div.app
       [:section.content
        [:pre
         [:code.javascript example-0]]]])))