#!/usr/bin/env boot

(set-env!
 :dependencies
 #(into % '[[http-kit "2.1.19"]
            [compojure "1.4.0"]
            [hiccup "1.0.5"]]))

(require '[compojure.core :refer [defroutes GET POST DELETE ANY context]]
         '[compojure.route :as route]
         '[hiccup.core :refer [html]]
         '[hiccup.page :refer [html5 include-css include-js]]
         '[org.httpkit.server :refer [run-server]])

(defn main-page [req]
  (html
    [:head
     [:meta {:charset "utf-8"}]
     [:title "grapple gun"]]
    [:body
     [:h1 "grapple gun"]
     [:div "This is a sample website that the Grapple Gun federated UI may inject itsself to"]
     [:div "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
     At vero eos et accusam et justo duo dolores et ea rebum.
     Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."]
     (include-js "/js/app.js")]))

(def port 3000)

(defn get-user-by-id [req]
  (let [user-id (-> req :params :id)
        password (-> req :params :password)]
    (clojure.string/join " " ["your new user-id is" user-id "with password" password])
    ))

(defroutes app
  (GET "/" [] main-page)
  #_(context "/user/:id" [user-id]
           (GET "/" [] get-user-by-id)
           (POST "/" [] get-user-by-id))
  (route/files "/" {:root "target"})
  (route/not-found "<p>Page not found.</p>")) ;; all other, return 404

(defn -main [& args]
  (let [shutdown (promise)
        stop-server (run-server app {:port port})]
    (do
      (.addShutdownHook (.. Runtime getRuntime)
                        (Thread. (fn []
                                   (do
                                     (println "shutting down..")
                                     (@stop-server)
                                     (deliver shutdown nil)))))
      (println "listening on " port)
      @shutdown)))
