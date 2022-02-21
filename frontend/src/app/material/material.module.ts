import { NgModule } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCardModule } from '@angular/material/card'
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'
import { MatSnackBarModule } from '@angular/material/snack-bar'
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { MatSelectModule } from '@angular/material/select';

const MODULES = [
  MatExpansionModule,
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatTooltipModule,
  MatCardModule,
  MatListModule,
  MatInputModule,
  MatTableModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatDialogModule,
  MatAutocompleteModule,
  MatSelectModule,
]

@NgModule({
  imports: [MODULES],
  exports: [MODULES]
})
export class MaterialModule { }
