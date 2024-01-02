// This is the "Offline page" service worker

importScripts('https://storage.googleapis.com/workbox-cdn/releases/5.1.2/workbox-sw.js');

const CACHE = "pwabuilder-page";

// TODO: replace the following with the correct offline fallback page i.e.: const offlineFallbackPage = "offline.html";
const offlineFallbackPage = "offline.html";

self.addEventListener("message", (event) => {
  if (event.data && event.data.type === "SKIP_WAITING") {
    self.skipWaiting();
  }
});

self.addEventListener("activate", () => {
  console.log("Service worker activate event!");
});

/** 서비스 워커가 설치 됐을 때 발생 **/
self.addEventListener('install', async (event) => {
  console.log('Service worker install event')
  event.waitUntil(
    caches.open(CACHE)
      .then((cache) => cache.add(offlineFallbackPage))
  );
});

if (workbox.navigationPreload.isSupported()) {
  workbox.navigationPreload.enable();
}

self.addEventListener('fetch', (event) => {
  // console.log('Fetch intercepted for : ', event.request.url)
  if (event.request.mode === 'navigate') {
    event.respondWith((async () => {
      try {
        const preloadResp = await event.preloadResponse;

        if (preloadResp) {
          return preloadResp;
        }

        const networkResp = await fetch(event.request);
        return networkResp;
      } catch (error) {

        const cache = await caches.open(CACHE);
        const cachedResp = await cache.match(offlineFallbackPage);
        return cachedResp;
      }
    })());
  }
});

/** 푸시 서비스가 메시지를 받았을 때 **/
self.addEventListener("push", (event) => {
  const payload = JSON.parse(event.data.text());
  event.waitUntil(
    registration.showNotification(payload.title, {
      body: payload.body,
      data: { link: payload.link },
    })
  );
});

/** 유저가 푸시 알림을 클릭했을 때 발생 **/
self.addEventListener("notificationclick", (event) => {
  clients.openWindow(event.notification.data.link);
});
