(ns status-doc.scenes.page
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]))

(defn page-scene []
  (let [active-page (subscribe [:get-active-page])]
    (fn []
      [:div.app
       [:section.content
        [:pre
         [:code.javascript
          "
          var a = 0;
          var b = a + 2; //result
          "]]]])))