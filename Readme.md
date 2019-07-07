[![Build Status](https://travis-ci.org/TheolZacharopoulos/DailyTradeReportingEngine.svg?branch=master)](https://travis-ci.org/TheolZacharopoulos/DailyTradeReportingEngine)

[![Coverage Status](https://coveralls.io/repos/github/TheolZacharopoulos/DailyTradeReportingEngine/badge.svg?branch=master&bust=1)](https://coveralls.io/github/TheolZacharopoulos/DailyTradeReportingEngine?branch=master)

[![BCH compliance](https://bettercodehub.com/edge/badge/TheolZacharopoulos/DailyTradeReportingEngine?branch=master&bust=1)](https://bettercodehub.com/)

# Daily Trade Reporting Engine

A simple daily trade reporting engine for incoming Orders.
Its input is a set of Orders and its output is a (daily) report printed in console.   

## The Order
The **Order** is a model which describes an Order to buy or sell given from various clients to the bank.
It includes information such as:
- *Entity*: A financial entity whose shares are to be bought or sold 
- *Trade Action*: Buy (Outgoing) or Sell (Incoming) 
- *Agreed FX*: The foreign exchange rate with respect to USD that was agreed 
- *Currency*: The currency in which Order is traded
- *Order Date*: Date on which the Order was sent to JP Morgan by various clients
- *Settlement Date*: The date on which the client wished for the Order to be settled with respect to Order Date
- *USD Amount*: Price per unit * Units * Agreed Fx (It is calculated during the object construction)

## The working days
Depending on the currency of each Order the settlement date may be change.
More specifically, Arabian has different working days than the rest of the world.
Therefore, a work week starts Monday and ends Friday, unless the currency of the trade is **AED** or **SAR**, 
where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.

This is represented in this code by the Strategy pattern described in the **Workingdays** abstract class.
There are 2 different subclasses of this class:
- *Arabia Working Days*: A strategy for Arabia in which work week starts Sunday and ends Thursday.
- *Default Working Days*: A strategy for the rest of the world for which a work week starts Monday and ends Friday.

The **OrderSettlementDateCalculator** static class uses the proper strategy based on the given currency of an Order.

## Statistics
The **OrderSettlementStatsCalculator** class is responsible of generating statistics based on a set of given Orders.

First, the *Outgoing Daily Amount* is calculated by grouping the Order with **BUY** action by settlement date 
and then sum up all the USD trade amount for each date.

Second, the *Incoming Daily Amount* is calculated by grouping the Order with **SELL** action by settlement date 
and then sum up all the USD trade amount for each date.

Third, the *Outgoing Daily Ranking* is calculated by first grouping the Order with **BUY** action by settlement date 
then sort by the USD trade amount and create a **Rank** object for each element.

Finally, the *Incoming Daily Ranking* is calculated by first grouping the Order with **SELL** action by settlement date 
then sort by the USD trade amount and create a **Rank** object for each element.

The *Rank* class represents the ranking, entity and date of a record.

## Reporting 
The **ReportGenerator** class is responsible of generating reports, using a StringBuilder, for the aforementioned statistics.

## Demo
In order to show its usage there is a **FakeOrdersGenerator** class that generates a set of dummy Orders. 
