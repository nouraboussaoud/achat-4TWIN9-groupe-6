// src/app/services/logger.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

export enum LogLevel {
  INFO = 'INFO',
  WARNING = 'WARNING',
  ERROR = 'ERROR',
  DEBUG = 'DEBUG'
}

@Injectable({
  providedIn: 'root'
})
export class LoggerService {
  private logFilePath = 'logs/frontend.log';
  private apiUrl = environment.apiUrl; // Utilisez l'URL de votre backend

  constructor(private http: HttpClient) { }

  info(message: string, context?: any): void {
    this.log(LogLevel.INFO, message, context);
  }

  warning(message: string, context?: any): void {
    this.log(LogLevel.WARNING, message, context);
  }

  error(message: string, context?: any): void {
    this.log(LogLevel.ERROR, message, context);
  }

  debug(message: string, context?: any): void {
    this.log(LogLevel.DEBUG, message, context);
  }

  private log(level: LogLevel, message: string, context?: any): void {
    const timestamp = new Date().toISOString();
    const logEntry = {
      timestamp,
      level,
      message,
      context: context ? JSON.stringify(context) : undefined
    };

    // Affichage des logs dans la console
    console.log(`${timestamp} - ${level} - ${message}`, context || '');

    // Envoi des logs au backend pour enregistrement dans un fichier
    this.saveLogToFile(logEntry);
  }

  private saveLogToFile(logEntry: any): void {
    this.http.post(`${this.apiUrl}/api/logs`, logEntry)
      .subscribe({
        next: () => {},
        error: (err) => console.error('Erreur lors de l\'enregistrement du log:', err)
      });
  }
}