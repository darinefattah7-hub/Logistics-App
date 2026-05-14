=============================================================================
WAREHOUSE LOGISTICS SYSTEM - COMPLETE PROJECT
=============================================================================

Student: Darine Abdul Fattah
Course: Object Oriented Programming 1 [PROG 211-EC10]
Instructor: Dr. Elias DOUMITH
Deadline: 17/05/2026

This project has been fully implemented according to the PDF requirements
(pages 1-6) and is ready for submission after you update the package name.

=============================================================================
WHAT'S INCLUDED
=============================================================================

 DOMAIN LAYER (Quiz Classes - Mandatory Reuse)
   ✓ Adjustable.java - Interface for adjustable objects
   ✓ Measurement.java - Quantity with unit (2 constructors, aggregation)
   ✓ Product.java - Product with auto-ID, implements Comparable
   ✓ ProductList.java - ArrayList that aggregates duplicates
   ✓ Warehouse.java - Manages inventory and shipments

 I/O LAYER (Interface-Based Abstraction)
   ✓ InputReader.java - Interface for input
   ✓ OutputWriter.java - Interface for output
   ✓ FileInputReader.java - Reads from files (try-catch exception handling)
   ✓ FileOutputWriter.java - Writes to files (try-catch exception handling)
   ✓ ConsoleInputReader.java - Reads from console
   ✓ ConsoleOutputWriter.java - Writes to console

 PROCESSING LAYER (Business Logic)
   ✓ WarehouseManager.java - Manages multiple warehouses
   ✓ ShipmentProcessor.java - Processes shipments with status tracking
   ✓ ShipmentRecord.java - Records shipment details (has nested enum)
   ✓ ReportGenerator.java - Generates all reports

 APPLICATION LAYER
   ✓ Main.java - Main application with menu-driven interface
   ✓ PDFScenarioRunner.java - Runs exact PDF scenario for testing
   ✓ ProjectTest.java - Comprehensive test suite

 INPUT FILES (Sample Data from PDF)
   ✓ warehouses.txt - Initial warehouses
   ✓ inventory.txt - Initial inventory
   ✓ operations.txt - Operations to process

 DOCUMENTATION
   ✓ PROJECT_VERIFICATION.txt - Requirements checklist
   ✓ FINAL_VERIFICATION.txt - Complete verification against PDF
   ✓ README.txt - This file





ARCHITECTURE (4 Layers):

1. DOMAIN LAYER
   - Pure business objects (Measurement, Product, Warehouse)
   - NO dependencies on I/O or other layers
   - Implements all quiz requirements

2. I/O LAYER
   - Interface-based abstraction (InputReader, OutputWriter)
   - Implementations for file and console
   - Proper exception handling with try-catch

3. PROCESSING LAYER
   - WarehouseManager: manages warehouses in HashMap
   - ShipmentProcessor: processes shipments, tracks statistics
   - ReportGenerator: creates reports using OutputWriter interface

4. APPLICATION LAYER
   - Main: coordinates everything, provides menu
   - Loads data (files or console)
   - Processes operations
   - Generates reports

SHIPMENT PROCESSING RULES:

  Available >= Requested → SUCCESSFUL
  Example: Ship 8 kg, have 10 kg → Success, move all 10 kg
  
  Available < Requested → PARTIAL
  Example: Ship 8 kg, have 5 kg → Partial, move all 5 kg
  
  Product/Warehouse missing → FAILED
  Example: Ship nonexistent product → Failed, move nothing

=============================================================================
 FEATURES IMPLEMENTED
=============================================================================

✓ Object-oriented design with clean separation
✓ Interface-based I/O (no domain → I/O dependencies)
✓ Collections for in-memory processing (ArrayList, HashMap)
✓ File-based AND interactive input (same logic)
✓ Proper exception handling (try-with-resources, multiple catch blocks)
✓ Robust to invalid input (doesn't crash)
✓ Product aggregation by name (case-insensitive)
✓ Shipment status tracking (successful/partial/failed)
✓ Report generation to console OR files
✓ Menu-driven interface
✓ NO external libraries (pure Java stdlib)

=============================================================================
PDF SCENARIO RESULTS
=============================================================================

When you run PDFScenarioRunner, you'll see:

OPERATIONS:
1. Create Warehouse W4 (Baalbek) with initial inventory → ✓
2. Add products to Beirut → ✓
3. Ship Apple from Zahle to Beirut (8 kg) → SUCCESSFUL
4. Ship Banana from Zahle to Baalbek (8 kg) → PARTIAL (only 5 kg)
5. Ship Grapes from Zahle to Tripoli (8 kg) → FAILED (no Grapes)
6. Ship Apple from Zahle to Saida (8 kg) → FAILED (Saida doesn't exist)

STATISTICS:
- Successful Shipments: 1
- Partial Shipments: 1
- Failed Shipments: 2  ← (PDF typo shows "1" but lists 2 items)

FINAL STATE:
- Zahle: EMPTY (both products shipped)
- Beirut: Apple (18 kg), Orange (7 kg), Mango (5 kg), Strawberry (30 box)
- Tripoli: EMPTY (no changes)
- Baalbek: Banana (5 kg), Apple (25 kg), Orange (20 kg), Mango (5 kg)

