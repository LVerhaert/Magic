import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
/*
Handles the logged messages
 */
export class MessageService {

  messages: string[] = [];

  constructor() {
  }

  /*
  Add message to the list of messages
   */
  add(message: string) {
    this.messages.push(message);
  }

  /*
  Clear the list of messages
   */
  clear() {
    this.messages = [];
  }
}
