importScripts(
  "https://www.gstatic.com/firebasejs/9.7.0/firebase-app-compat.js"
);
importScripts(
  "https://www.gstatic.com/firebasejs/9.7.0/firebase-messaging-compat.js"
);

const messaging = firebase.messaging();

messaging.onBackgroundMessage(function (payload) {
  const notification = payload.notification;

  const notificationTitle = notification.title;
  const notificationOptions = {
    body: notification.body,
    icon: "/2.png", // 루트 경로 기준으로 접근
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
})