(ns cloudhackers.servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require
   [ring.util.servlet :as servlet]
   [net.cgrand.moustache :as moustache]
   [ring.middleware.reload :as reload]))

(def my-app
     (moustache/app
      (reload/wrap-reload '(cloudhackers.servlet))
      ["hi"] {:get "hello world!"}))

(servlet/defservice my-app)
