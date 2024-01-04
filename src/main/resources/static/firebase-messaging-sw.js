importScripts(
  "https://www.gstatic.com/firebasejs/10.7.1/firebase-app-compat.js"
);
importScripts(
  "https://www.gstatic.com/firebasejs/10.7.1/firebase-messaging-compat.js"
);

const VAPID_KEY = '[[${@environment.getProperty("firebase.vapid.key")}]]'
const API_KEY = '[[${@environment.getProperty("firebase.api.key")}]]'
const AUTH_DOMAIN = '[[${@environment.getProperty("firebase.auth.domain")}]]'
const PROJECT_ID = '[[${@environment.getProperty("firebase.project.id")}]]'
const STORAGE_BUCKET = '[[${@environment.getProperty("firebase.storage.bucket")}]]'
const MESSAGING_SENDER_ID = '[[${@environment.getProperty("firebase.messaging.sender.id")}]]'
const APP_ID = '[[${@environment.getProperty("firebase.app.id")}]]'
const MEASUREMENT_ID = '[[${@environment.getProperty("firebase.measurement.id")}]]'

const firebaseApp = firebase.initializeApp({
  apiKey: API_KEY,
  authDomain: AUTH_DOMAIN,
  projectId: PROJECT_ID,
  storageBucket: STORAGE_BUCKET,
  messagingSenderId: MESSAGING_SENDER_ID,
  appId: APP_ID,
  measurementId: MEASUREMENT_ID
});

const messaging = firebase.messaging(firebaseApp);

messaging.onBackgroundMessage(function (payload) {
  const notification = payload.notification;

  const notificationTitle = notification.title;
  const notificationOptions = {
    body: notification.body,
    icon: "/2.png", // 루트 경로 기준으로 접근
  };
  self.registration.showNotification(notificationTitle, notificationOptions);
})