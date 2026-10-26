# MiniBank — Console Banking Application (CEUE203 Part-B Project Work)

**Institution:** Charotar University of Science and Technology (CHARUSAT)  
**Faculty:** Faculty of Technology and Engineering (FTE)  
**Subject:** Object-Oriented Programming (CEUE203) — Java  
**Student Name:** Shrey Patel  
**Roll Number / Student ID:** 25CE090  
**Semester / Year:** 3rd Semester, Academic Year 2026–27  
**Repository:** https://github.com/shreypatell13/oop-minibank-25CE090  

---

## 📌 Project Overview
MiniBank is a modular, multi-tier console banking system developed across all 14 practicals in accordance with the CEUE203 syllabus requirements. It progressively integrates object-oriented paradigms, robust domain validation, reflection-driven metadata processing, custom exception handling, concurrent transaction synchronization, file persistence, and generic repository architectures.

---

## 🗂 Practical Directory & Milestones

| Practical | Module Package | Key Features & Concepts | Requirements Covered |
|:---:|:---|:---|:---:|
| **P1** | `src/p01_menu/` | Application header (`record BankInfo`), menu choices (`enum MenuOption`), Scanner input, switch expression loop | **6/6 Requirements** |
| **P2** | `src/p02_encapsulation/` | Encapsulated `Customer` & `Account` entities, static ID generators (`CUST101`, `AC0001`), constructor chaining, balance protection | **5/5 Requirements** |
| **P3** | `src/p03_overrides/` | `toString()`, `equals()` and `hashCode()` overrides, static nested `Address` class, `Cloneable` deep copy, `instanceof` checks | **5/5 Requirements** |
| **P4** | `src/p04_validation/` | Regex-backed `Validator` (Mobile, Email, PAN, IFSC), `Command` record & parser, `StatementFormatter` (`StringBuilder`) | **5/5 Requirements** |
| **P5** | `src/p05_polymorphism/` | Abstract `Account` base, `SavingsAccount` (4.0%), `CurrentAccount` (overdraft), `FixedDepositAccount` (7.0%), polymorphic array | **6/6 Requirements** |
| **P6** | `src/p06_interfaces/` | `Transactable`, `InterestBearing` with default method, `WithdrawRule` functional interface (lambda + anonymous class), `Premium` marker | **6/6 Requirements** |
| **P7** | `src/p07_annotations/` | Custom runtime annotations (`@Id`, `@Positive`, `@MaxLength`), reflection-driven `AnnotationValidator` inspecting fields dynamically | **5/5 Requirements** |
| **P8** | `src/p08_exceptions/` | Custom checked `BankException` hierarchy, `InsufficientFundsException` with shortfall tracking, try-with-resources with `AutoCloseable` | **4/4 Requirements** |
| **P9** | `src/p09_threads/` | `AccountWorker` (`Runnable`), 10-thread concurrency demo, race condition mitigation with synchronized methods | **4/4 Requirements** |
| **P10** | `src/p10_concurrency/` | Managed thread pool (`Executors.newFixedThreadPool(4)`), producer-consumer synchronization (`wait`/`notify`), canonical deadlock prevention | **4/4 Requirements** |
| **P11** | `src/p11_persistence/` | Object serialization (`Serializable`), `StatePersister` save/load, append-only `TransactionLog`, NIO `ReportGenerator` summary | **5/5 Requirements** |
| **P12** | `src/p12_collections/` | `AccountStore` (`HashMap`), transaction audit log (`ArrayList`), balance sorting with `Comparator`, `Comparable` account ordering | **6/6 Requirements** |
| **P13** | `src/p13_generics/` | Generic `Repository<T>`, reflection-based `@Id` primary key resolution, `@SafeVarargs saveAll`, generic bounded method `<N extends Number>` | **5/5 Requirements** |
| **P14** | `src/p14_capstone/` | Comprehensive `BankService` wiring repository, validation, and exceptions; end-to-end menu integration; JUnit 5 test runner suite | **6/6 Requirements** |

---

## 🛠 Compilation and Execution

To run any practical module, navigate to the `src/` directory and compile with `javac`:

```bash
# Example: Running Practical 1
javac src/p01_menu/MiniBank.java
java -cp src p01_menu.MiniBank

# Example: Running Practical 14 (Capstone)
javac src/p14_capstone/MiniBankPart14.java
java -cp src p14_capstone.MiniBankPart14
```

---

## 📄 Submission Documentation
- **Word Document:** [`docs/25CE090_MiniBank_PartB_Project_(1-14).docx`](docs/25CE090_MiniBank_PartB_Project_(1-14).docx)
- **PDF Document:** [`docs/25CE090_MiniBank_PartB_Project_(1-14).pdf`](docs/25CE090_MiniBank_PartB_Project_(1-14).pdf)
