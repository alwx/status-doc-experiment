(ns status-doc.scenes.main
  (:require-macros [status-doc.web :refer [defsnippet defdocs]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as string]
            [clojure.string :as str]))

(defsnippet snippet-basic "snippet-basic")

(defdocs docs ["command"
               "command/name"])

(defn linkify [js token]
  (string/replace js #"\$(.*)\$" (fn [[_ link-arg]]
                                   (let [[link-text link-path] (str/split link-arg #"#")]
                                     (-> [:a {:class (when (= token link-path)
                                                       :active)
                                              :href  (str "#" link-path)}
                                          link-text]
                                         (r/as-element)
                                         (r/render-to-string)
                                         (str))))))

(defn main-scene []
  (let [token (subscribe [:get-token])]
    (fn []
      [:div.container
       [:section.content
        [:pre
         [:code.javascript
          {:dangerouslySetInnerHTML {:__html (linkify snippet-basic @token)}}]]
        [:div.doc
         {:dangerouslySetInnerHTML {:__html (get docs @token)}}]]])))