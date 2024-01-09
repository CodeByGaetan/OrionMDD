import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { PostService } from '../../services/post.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.scss']
})
export class ListPostComponent implements OnInit {

  public pageIndex = 0;
  public pageSize = 10;

  public asc: boolean = false;

  public posts!: Post[];
  public totalSize!: number;

  public onError = false;

  constructor(
    private postService: PostService,
    private router: Router
  ) { }

  private fetchData() {
    this.postService.getAllPagedSorted(this.pageIndex, this.pageSize, this.asc).subscribe({
      next: (response) => {
        this.posts = response.items;
        this.totalSize = response.totalItems;
      },
      error: (error) => this.onError = true
    })
  }

  public ngOnInit(): void {
    this.fetchData();
  }

  public toggleSort() {
    this.asc = this.asc ? false : true;
    this.fetchData();
  }

  onPageChange($pageEvent: PageEvent) {
    this.pageSize = $pageEvent.pageSize;
    this.pageIndex = $pageEvent.pageIndex;
    this.fetchData();
  }

  public goPost(postId: number): void {
    this.router.navigateByUrl(`/posts/${postId}`);
  }

}
