import { APP_INITIALIZER, ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { CalendarModule, DateAdapter, MOMENT } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { SchedulerModule } from 'angular-calendar-scheduler';
import moment from 'moment';
import { provideAnimations } from '@angular/platform-browser/animations';
import { KeycloakService } from 'keycloak-angular';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'neo-book',
        clientId: 'neo-book-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    importProvidersFrom([
      CalendarModule.forRoot({
        provide: DateAdapter,
        useFactory: adapterFactory,
      }),
      SchedulerModule.forRoot({
        locale: 'en',
        headerDateFormat: 'daysRange',
        logEnabled: true,
      })
    ]),
    { provide: MOMENT, useValue: moment },
    provideAnimations(),
    provideHttpClient(withInterceptors([])),
    KeycloakService
  ]
};
