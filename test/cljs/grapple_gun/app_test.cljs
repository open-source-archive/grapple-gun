(ns grapple-gun.app-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [cljs.test :as t]
            [grapple-gun.app :as app]))

(deftest can-get-container []
  (is (= (type (app/get-container)) js/HTMLDivElement) "unable to get container element"))
