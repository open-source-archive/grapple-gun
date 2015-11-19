(ns grapple-gun.app
  (:require [reagent.core :as reagent :refer [atom]]
            [garden.core :refer [css]]))

(def main-css (css
                [:body {:margin-top "50px"}
                 [:#grapple-gun-container {:background-color "lightgray"
                                           :position "fixed"
                                           :width "100%"
                                           :height "50px"
                                           :top 0
                                           :left 0}
                  [:ol.items {:list-style-type "none"
                              :display "flex"}
                   [:li {:font-family "Verdana"
                         :margin-right 15}]]]]))


(defn grapple-gun []
  [:div
   [:style main-css]
   [:ol.items
    [:li "YourTurn"]
    [:li "some"]
    [:li "other"]
    [:li "website"]]])


(defn get-container
  "create <div id='grapple-gun-container'> or return it, if it already exists"
  []
  (let [body (.-body js/document)
        existing-container (.getElementById js/document "grapple-gun-container")
        container (if (nil? existing-container)
                    (do (.insertBefore body
                                   (.createElement js/document "div")
                                   (.-firstChild body)))
                    existing-container)]
    (.setAttribute container "id" "grapple-gun-container")
    container))

(defn init []
    (reagent/render-component [grapple-gun] (get-container)))
