import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import paginate from 'jw-paginate';

@Component({selector: 'app-paginator',
templateUrl: './paginator.component.html',
styleUrls: ['./paginator.component.css']
})

export class PaginatorComponent implements OnInit, OnChanges {
    @Input() items!: Array<any>;
    @Output() changePage = new EventEmitter<any>(true);
    @Input() initialPage = 1;
    @Input() pageSize = 5;
    @Input() maxPages = 20;

    pager: any = {};

    ngOnInit() {
        if (this.items && this.items.length) {
            this.setPage(this.initialPage);
        }
    }

    ngOnChanges(changes: SimpleChanges) {
        if (changes.items.currentValue !== changes.items.previousValue) {
            this.setPage(this.initialPage);
        }
    }

    setPage(page: number) {
        this.pager = paginate(this.items.length, page, this.pageSize, this.maxPages);

        var pageOfItems = this.items.slice(this.pager.startIndex, this.pager.endIndex + 1);

        this.changePage.emit(pageOfItems);
    }
}