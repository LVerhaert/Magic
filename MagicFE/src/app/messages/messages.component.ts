import {Component, OnInit} from '@angular/core';
import {MessageService} from '../services/message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
/*
List of messages produced by the message service, for debugging
 */
export class MessagesComponent implements OnInit {

  constructor(public messageService: MessageService) {
  }

  ngOnInit(): void {
  }

}
