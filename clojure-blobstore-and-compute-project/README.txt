this demo uses jclouds, so checkout github/jclouds/jclouds for more

mvn clojure:repl


then, commands like the following will work:

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; test your connectivity to any of the supported blobstore clouds
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'org.jclouds.blobstore)

(def blobstore-accounts [{:service :atmosonline :account "account" :key "key"   }
                         {:service :azureblob   :account "account" :key "key"   }
                         {:service :cloudfiles  :account "account" :key "key"   }
                         {:service :transient   :account "dummy"   :key "dummy" }
                         {:service :s3          :account "account" :key "key"   }
                         {:service :synaptic    :account "account" :key "key"   }

;; this prints the count of containers in your account and the service delay
(defn containercount-performance [account]
  (let [credentials (blobstore (name(account :service)) (account :account) (account :key))
        container-count (time (count (containers credentials)))]
    (println (:service account) " " container-count)
    (.close (blobstore-context credentials))))

(dorun (map containercount-performance blobstore-accounts))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; test your connectivity to any of the supported compute clouds
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'org.jclouds.compute)


;; ex to override an endpoint
(System/setProperty "bluelock.endpoint" "http://174.47.114.135/api")
(System/setProperty "vcloud.endpoint"   "http://174.47.114.135/api")

(def compute-accounts [{:service :ec2            :account: "account" key: "key"   }
                    ;;  {:service :hostingdotcom    :account: "account" key: "key"   }
                      {:service :cloudservers    :account: "account" key: "key"   }
                      {:service :stub            :account: "dummy"   key: "dummy" }
                      {:service :vcloud          :account: "account" key: "key"   }
                      {:service :bluelock        :account: "account" key: "key"   }
                      {:service :terremark       :account: "account" key: "key"   }
                      {:service :ibmdev          :account: "account" key: "key"   }
                      {:service :rimuhosting     :account: "account" key: "key"   }
                      {:service :gogrid          :account: "account" key: "key"   }

;; this prints the count of nodes in your account and the service delay
(defn nodecount-performance [account]
  (let [credentials (compute-service (name(account :service)) (account :account) (account :key))
        node-count (time (count (nodes credentials)))]
    (println (:service account) " " node-count)
    (.close (compute-context credentials))))

(dorun (map nodecount-performance compute-accounts))
