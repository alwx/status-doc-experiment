(ns status-doc.scenes.snippet
  (:require-macros [status-doc.web :refer [defsnippets defdocs]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [clojure.string :as string]))

(defsnippets snippets ["basic"])
(defdocs docs ["command/name"])

(defn update-links [js token]
  (string/replace js
                  #"\$(.*)\$"
                  (fn [[_ link-arg]]
                    (let [[link-text link-path] (string/split link-arg #"#")]
                      (-> [:a {:class (when (= token link-path)
                                        :active)
                               :href  (str "#" link-path)}
                           link-text]
                          (reagent/as-element)
                          (reagent/render-to-string)
                          (str))))))

(defn scene []
  [:section.content
   [:pre
    [:code.javascript
     {:dangerouslySetInnerHTML {:__html (update-links (get snippets "basic") "")}}]]
   [:div.doc
    {:dangerouslySetInnerHTML {:__html (get docs "")}}]])