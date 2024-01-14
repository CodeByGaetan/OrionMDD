import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {

  @Input() pageIndex!: number;
  @Input() pageSize!: number;
  @Input() totalSize!: number;
  @Output() pageChanged: EventEmitter<PageEvent> = new EventEmitter();

  pageSizeOptions = [5, 10, 25];
  hidePageSize = false;
  showPageSizeOptions = true;

  constructor() { }

  ngOnInit(): void {
  }

  handlePageEvent($pageEvent: PageEvent) {
    this.pageChanged.emit($pageEvent);
  }

}
