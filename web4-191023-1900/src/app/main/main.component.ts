import {Component, AfterViewInit, OnInit} from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, AfterViewInit{

  y_select: number = 0;
  x_select: string = '0';
  r_select: string = '0';

  ngAfterViewInit() {
    //We're loading the player script on after view is loaded
    // @ts-ignore
    import('../../assets/js/graph.js');
  }

  ngOnInit(): void {

  }

}
