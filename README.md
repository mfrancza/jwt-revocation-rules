# jwt-revocation-rules

Multiplatform library for describing and applying JWT revocation rules managed by the jwt-revocation-manager.

## Model

### Rule

A Rule is a collection of Conditions applying to the values of the claims in a JWT token. For each claim, there may be zero to many Conditions and a Rule is meet if every Condition it contains is met.

```json
{
  "ruleId": "1bbe5415-4469-480e-b4ab-ac2447387270",
  "ruleExpires": 1778156822,
  "iss": [
    {
      "operation": "=",
          "value": "https://bad-issuer.mfrancza.com/"
    }
  ],
  "iat": [
    {
      "operation": "<",
      "value": 1678156822
    }
  ]
}
```

### Condition

A Condition is an assertion about the value of a Claim.  The available Conditions depend on the data type of the claim.  A condition is defined by an operation and comparison value.

```json
{
  "operation": "<",
  "value": 1678156822
}
```

### RuleSet

A RuleSet contains one or more Rules and a timestamp.   A RuleSet is met if one or more of the Rules in it are met by the claims in the JWT.  RuleSets are the format rules are distributed to consumers in and the timestamp is used to control caching.

```json
{
  "rules": [
    {
      "ruleId": "1bbe5415-4469-480e-b4ab-ac2447387270",
      "ruleExpires": 1778156822,
      "iss": [
        {
          "operation": "=",
          "value": "https://bad-issuer.mfrancza.com/"
        }
      ]
    }
  ],
  "timestamp": 1678159947
}
```

## Serialization

This library defines the serialized forms of the Rules as well as the Kotlin types.  For the most part, the types are serialized to JSON objects with properties with the same name.

The exception is Conditions, which have an operation JSON property that is used to determine the Condition subtype during deserialization but is not a Kotlin property.





